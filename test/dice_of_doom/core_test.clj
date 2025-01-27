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

(deftest player-test
  (testing
   (let [test-data [[1 2] [2 3] [3 4]]]
     (is (= (player test-data 1) 2))
     (is (= (player test-data 0) 1))
     (is (= (player test-data 2) 3)))))

(deftest dice-test
  (testing
   (let [test-data [[1 2] [2 3] [3 4]]]
     (is (= (dice test-data 0) 2))
     (is (= (dice test-data 1) 3))
     (is (= (dice test-data 2) 4)))))

(deftest neighbors-test
  (testing
   (is (= (neighbors 0) '(2 1 3)))
    (is (= (neighbors 1) '(3 0)))
    (is (= (neighbors 2) '(0 3)))
    (is (= (neighbors 3) '(1 0 2)))))
