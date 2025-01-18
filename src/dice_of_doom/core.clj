(ns dice-of-doom.core
  (:require [clojure.string :as str]
            [clojure.test :refer :all]))

(def num-players 2)
(def max-dice 3)
(def board-size 2)
(def board-hexnum (* board-size board-size))

(defn board-array [lst]
  (vec lst))

(defn player-letter [n]
  (char (+ n 97)))

(defn gen-board
  "Generate a random sequence that forms the basis of a game board.
   
   ## Example
   ```clojure
    (gen-board)
    ;; [[1 3] [0 1] [0 1] [0 1]]
   ```
   "
  []
  (board-array (for [n (range board-hexnum)]
                 (vector
                  (rand-int num-players)
                  (inc (rand-int max-dice))))))

(defn draw-board
  "Draw game board.
   
   ## Example
   ```clojure
   (draw-board (gen-board))
    ;;   b-1 b-2 
    ;;  b-1 b-2 
   ```"
  [board]
  (doseq [y (range board-size)]
    (dotimes [_ (- board-size y)]
      (print " "))
    (doseq [x (range board-size)]
      (let [hex (nth board (+ x (* board-size y)))]
        (print (str (player-letter (first hex))
                    "-"
                    (second hex)
                    " "))))
    (print "\n")))

(draw-board (gen-board))

