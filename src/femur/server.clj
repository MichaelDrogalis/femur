(ns femur.server
  (:require [noir.server :as server]
            [noir.response :refer [json]]
            [cheshire.core :refer [generate-string parse-string]]
            [ring.middleware.format-params :as format-params]
            [noir.core :refer [defpage]]
            [dibble.core :refer [seed-table]]))

(server/add-middleware format-params/wrap-json-params)

(defn keyify-vendor [args]
  (assoc (:database args) :vendor (keyword (:vendor (:database args)))))

(defn format-commands [commands]
  (map (fn [command] (map #(or (keyword %) %) command)) commands))

(defn write-seeds! [[args & instructions]]
  (let [keyified-args (parse-string (generate-string args) true) ;;; Gross hack to format JSON
        db-args (assoc keyified-args :database (keyify-vendor keyified-args))]
    (apply (partial seed-table db-args) (format-commands instructions))))

(defpage [:post "/dibble"] {:as params}
  (dorun (map write-seeds! (get params "args")))
  (json {:success :true}))

(defn -main [& m]
  (let [mode (keyword (or (first m) :dev))
        port (Integer. (get (System/getenv) "PORT" "8080"))]
    (server/start port {:mode mode
                        :ns 'femur})))

