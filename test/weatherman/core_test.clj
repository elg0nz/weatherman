(ns weatherman.core-test
  (:require [clojure.test :refer :all]
            [weatherman.core :refer :all]
            [weatherman.formaters-test :refer :all]
            [weatherman.conversion-test :refer :all]
            ))

(run-tests 'weatherman.conversion-test)
(run-tests 'weatherman.formaters-test)
