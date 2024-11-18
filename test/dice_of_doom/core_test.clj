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

(deftest test-tag-macro
  (testing "tag macro generates correct HTML tags"
    (let [output (with-out-str (tag "a-tag" [:class "my-class"]))]
      (is (= "<a-tag class=\"my-class\"></a-tag>" output)))

    (let [output (with-out-str (tag "a-tag" '(:class "my-class")))]
      (is (= "<a-tag class=\"my-class\"></a-tag>" output)))))

(deftest test-html-macro
  (testing "html macro generates correct HTML tags"
    (let [output (with-out-str (html))]
      (is (= "<html></html>" output)))

    (let [output (with-out-str (html "Hello, HTML!"))]
      (is (= "<html>Hello, HTML!</html>" output)))))

(deftest test-body-macro
  (testing "body macro generates correct HTML tags"
    (let [output (with-out-str (body))]
      (is (= "<body></body>" output)))

    (let [output (with-out-str (body "Hello, BODY!"))]
      (is (= "<body>Hello, BODY!</body>" output)))))