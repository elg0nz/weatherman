(ns weatherman.conversion-test
  (:require [clojure.test :refer :all]
            [weatherman.conversions :refer :all]))

(deftest f-to-c-test
  (is (= (farenheit-to-celcius 54) "12.222222")))
