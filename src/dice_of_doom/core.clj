(ns dice-of-doom.core
  (:require [clojure.string :as str]
            [clojure.test :refer :all]))

(def num-players 2)
(def max-dice 3)
(def board-size 2)
(def borad-hexnum (* board-size board-size))

(defn board-array [lst]
  (vec lst))


