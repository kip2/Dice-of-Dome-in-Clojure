(ns dice-of-doom.core-test
  (:require [clojure.test :refer :all]
            [dice-of-doom.core :refer :all]))

(deftest board-array-test
  (testing
   (is (= (board-array '(1 2 3)) [1 2 3]))
    (is (= (board-array '(-1 -2 -3)) [-1 -2 -3]))
    (is (= (board-array '("a" "b" "c")) ["a" "b" "c"]))
    (is (= (board-array '(1.0 2.0 3.0)) [1.0 2.0 3.0]))))

(deftest player-letter-test
  (testing
   (is (= (player-letter 0) \a))
    (is (= (player-letter 1) \b))
    (is (= (player-letter 2) \c))
    (is (= (player-letter 25) \z))))

