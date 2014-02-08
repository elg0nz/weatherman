(ns weatherman.core
  (:gen-class))
(require '[clj-http.client :as client])
(require '[clojure.java.io :as io])
(require '[weatherman.conversions :as conversions])
(require '[weatherman.formaters :as formaters])

(def fetch-weather
  (let [sf-weather-json "http://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20weather.forecast%20where%20woeid%3D12797154&format=json&diagnostics=true&callback="]
    (client/get sf-weather-json)))

(def display
  (let
    [item (->> fetch-weather (formaters/read-body) (formaters/get-item))]
    [ (->> item (formaters/get-temp) (Integer/parseInt) (conversions/farenheit-to-celcius))
      (->> item (formaters/get-condition-text))
      (->> item formaters/get-forecast)]))

(defn -main
  [& raw-args]
  (print display))
