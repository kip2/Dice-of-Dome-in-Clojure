(ns dice-of-doom.core
  (:require [clojure.test :refer :all]))

(def num-players 2)
(def max-dice 3)
(def board-size 2)
(def board-hexnum (* board-size board-size))

(defn board-array [lst]
  (vec lst))

(defn player-letter
  "Converts a numeric value into a character.

  ## Example
  ```clojure
  (player-letter 1)
  ;; \\a
  ```
  "
  [n]
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

(defn game-tree [board player spare-dice first-move]
;; todo: implement me!
  nil)

(defn add-new-dice [board player spare-dice]
  (letfn [(f [lst n]
            (cond (nil? lst) nil
                  (zero? n) lst
                  :else (let [cur-player (first (first lst))
                              cur-dice (first (rest (first lst)))]
                          (cond (and (= cur-player player) (< cur-dice max-dice))
                                (cons (list cur-player (inc cur-dice))
                                      (f (rest lst) (dec n)))
                                :else (cons (first lst) (f (rest lst) n))))))]
    (board-array (f (into '() board) spare-dice))))

(defn add-passing-move [board player spare-dice first-move moves]
;; todo: implement me!
  nil)

(defn neighbors
  "Return a list of adjacent cells for a specified cell on a hexagonal game board.

  The game board is reppresented as a hexagonal grid:

      0  1
    2  3

  Adjacent cells are calculated based on the board layout:
  - Cell 0 is adjacent to cells 1 and 2 and 3.
  - Cell 1 is adjacent to cells 0 and 3.
  - Cell 2 is adjacent to cells 0 and 3.
  - Cell 3 is adjacent to cells 0 and 1 and 2.

  ## Example
  ```clojure
  (neighbors 0)
  ;; (2 1 3)

  (neighbors 2)
  ;; (0 3)
  ```
  "
  [pos]
  (let [up (- pos board-size)
        down (+ pos board-size)]
    (for [p (concat (list up down)
                    (when-not (zero? (mod pos board-size))
                      (list (dec up) (dec pos)))
                    (when-not (zero? (mod (inc pos) board-size))
                      (list (inc pos) (inc down))))
          :when (and (>= p 0) (< p board-hexnum))]
      p)))

(defn player [board pos]
  (first (get board pos)))

(defn dice [board pos]
  (second (get board pos)))

(defn attacking-moves [board cur-player spare-dice]
;; todo: inplemente me!
  nil)
