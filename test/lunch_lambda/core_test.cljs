(ns lunch-lambda.core-test
  (:require [cljs.test :refer-macros [is are deftest testing use-fixtures]]
            [lunch-lambda.core :as core]))

(deftest test-request-has-no-secret
  (is (= {:status 403}
         (core/save {} {} {} "secrets"))))

(deftest test-request-has-secret-saving-allowed
  (let [response (core/save {} {} {:request-secret "secrets"} "secrets")]
    (is (= 200
           (:status response)))))

(deftest test-there-is-no-secret-saving-allowed
  (let [response (core/save {} {} {} nil)]
    (is (= 200
           (:status response)))))
