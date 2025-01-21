(ns dice-of-doom.core
  (:require [clojure.test :refer :all]))

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
    ;;   b-1 a-2 
    ;;  b-2 a-3 

    (draw-board [[0 3] [0 3] [1 3] [1 1]])
    ;;   a-3 a-3 
    ;;  b-3 b-1 
   ```
   "
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

;; todo: 正しい出力を確定し、テストコードを書く
(defn board-attack [board player src dst dice]
  (map-indexed
   (fn [pos hex]
     (cond (= pos src) (list player 1)
           (= pos dst) (list player (dec dice))
           :else hex))
   board))

(defn player [board pos]
  (first (get board pos)))


