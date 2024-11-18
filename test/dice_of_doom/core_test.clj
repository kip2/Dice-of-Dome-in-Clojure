(ns dice-of-doom.core-test
  (:require [clojure.test :refer :all]
            [dice-of-doom.core :refer :all]))

(deftest test-print-tag
  (testing "print-tag generates correct HTML tags"
    (let [output (with-out-str (print-tag "div" {:class "my-class" :id "main"} false))]
      (is (= "<div class=\"my-class\" id=\"main\">" output)))

    (let [output (with-out-str (print-tag "DIV" {:class "my-class" :id "main"} true))]
      (is (= "</div class=\"my-class\" id=\"main\">" output)))

    (let [output (with-out-str (print-tag "span" {} false))]
      (is (= "<span>" output)))

    (let [output (with-out-str (print-tag "BR" {} true))]
      (is (= "</br>" output)))

    (let [output (with-out-str (print-tag "a-tag" '(("color" "blue") ("height" 9)) false))]
      (is (= "<a-tag color=\"blue\" height=\"9\">" output)))

    (let [output (with-out-str (print-tag "a-tag" '(("color" "blue") ("height" 9)) nil))]
      (is (= "<a-tag color=\"blue\" height=\"9\">" output)))

    (let [output (with-out-str (print-tag "a-tag" '(("color" "blue") ("height" 9)) true))]
      (is (= "</a-tag color=\"blue\" height=\"9\">" output)))

    (let [output (with-out-str (print-tag "a-tag" nil true))]
      (is (= "</a-tag>" output)))))

