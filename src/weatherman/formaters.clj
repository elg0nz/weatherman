(ns weatherman.formaters)
(require '[clojure.data.json :as json])

(defn read-body
  [document]
  (json/read-str
    (get document :body )))

(defn- hash-to-json
  [input] (json/write-str input))

(defn board
  ([input] (hash-to-json {:info input, :color :darko}))
  ([input color] (hash-to-json {:info input, :color color})))

(defn get-item
  [body]
  (get-in body ["query" "results" "channel" "item"]))

(defn get-temp
  [item]
  (get-in item ["condition", "temp"]))

(defn get-condition-text
  [item]
  (get-in item ["condition", "text"]))

(defn get-forecast
  [item]
  (get item "forecast"))
