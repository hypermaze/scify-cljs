(ns scify-clj.core-test
  (:require [cljs.test :refer-macros [deftest testing is]]
            [scify-clj.core :as core]))

(deftest fake-test
  (testing "fake description"
    (is (= 1 2))))
