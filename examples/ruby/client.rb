require 'json'
require 'curb'

instructions =
  {:args =>
  [[{:database => {:db => :simulation, :user => "root", :password => "", :vendor => :mysql},
      :table => :people, :n => 10},
    [:randomized, :name, :length, 5]]]}

http = Curl.post("http://localhost:8080/dibble", instructions.to_json) do |curl|
  curl.headers['Accept'] = 'application/json'
  curl.headers['Content-Type'] = 'application/json'
  curl.headers['Api-Version'] = '2.2'
end

puts http.body_str

