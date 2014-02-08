(ns weatherman.core
  (:gen-class))
(require '[clj-http.client :as client])
(require '[clojure.java.io :as io])
(require '[weatherman.conversions :as conversions])
(require '[weatherman.formaters :as formaters])
(require '[taoensso.carmine :as car :refer (wcar)])

(def server1-conn
  {:pool {} :spec {:host "127.0.0.1" :port 6379}})

(defmacro wcar* [& body] `(car/wcar server1-conn ~@body))

(defn store-in-redis
  [statuses]
  (wcar* (car/lpush "status_msgs" (formaters/board statuses)))
  (wcar* (car/publish "statuses" "ok")))

(def fetch-weather
  (let [sf-weather-json "http://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20weather.forecast%20where%20woeid%3D12797154&format=json&diagnostics=true&callback="]
    (client/get sf-weather-json)))

(def display
  (let
    [item (->> fetch-weather (formaters/read-body) (formaters/get-item))]
    [ (->> item (formaters/get-temp) (Integer/parseInt) (conversions/farenheit-to-celcius))
      (->> item (formaters/get-condition-text))
      ]))

(defn -main
  [& raw-args]
  (print display)
  (store-in-redis display))
