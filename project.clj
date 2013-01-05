(defproject femur "0.1.0-SNAPSHOT"
  :description "A server for interacting with Dibble over common data formats"
  :url "https://github.com/MichaelDrogalis/femur"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.0-RC1"]
                 [noir "1.3.0-beta3"]
                 [dibble "0.2.0-beta1"]]
  :main femur.server)

