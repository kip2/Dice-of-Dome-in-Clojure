(ns dice-of-doom.core-test
  (:require [clojure.test :refer :all]
            [dice-of-doom.core :refer :all]))

(deftest board-array-test
  (testing
   (is (= (board-array '(1 2 3)) [1 2 3]))
    (is (= (board-array '(-1 -2 -3)) [-1 -2 -3]))
    (is (= (board-array '("a" "b" "c")) ["a" "b" "c"]))
    (is (= (board-array '(1.0 2.0 3.0)) [1.0 2.0 3.0]))))
