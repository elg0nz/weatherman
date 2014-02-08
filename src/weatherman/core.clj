(ns weatherman.core
  (:gen-class))
(require '[clj-http.client :as client])
(require '[clojure.data.json :as json])
(require '[clojure.java.io :as io])

(def fetch-weather
  (let [sf-weather-json "http://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20weather.forecast%20where%20woeid%3D12797154&format=json&diagnostics=true&callback="]
    (client/get sf-weather-json)))

(defn read-body
  [document]
  (json/read-str
    (get document :body )))

(defn get-item
  [body]
  (get-in body ["query" "results" "channel" "item"]))

(defn get-temp
  [item]
  (get-in item ["condition", "temp"]))

(defn farenheit-to-celcius
  "Turns F to C using this formula: ((F - 32) * 5) / 9 "
  [number]
  (str
    (float
      (/
        (* 5
           (- number 32))
        9))))

(defn get-condition-text
  [item]
  (get-in item ["condition", "text"]))

(defn get-forecast
  [item]
  (get item "forecast"))

(def display
  (let
    [item (->> fetch-weather (read-body) (get-item))]
    [ (->> item (get-temp) (Integer/parseInt) (farenheit-to-celcius))
      (->> item (get-condition-text))
      (->> item get-forecast)]))

(defn -main
  [& raw-args]
  (print display))
