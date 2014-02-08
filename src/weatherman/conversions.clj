(ns weatherman.conversions)

(defn farenheit-to-celcius
  "Turns F to C using this formula: ((F - 32) * 5) / 9 "
  [number]
  (str
    (float
      (/
        (* 5
           (- number 32))
        9))))

