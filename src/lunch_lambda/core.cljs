(ns lunch-lambda.core
  (:require [cljs-lambda.macros :refer-macros [defgateway]]
            [eulalie.creds :as creds]
            [cognitect.transit :as t]
            [lunch-lambda.dynamo :as db]))

(def reader (t/reader :json))
(def credentials (assoc (creds/env) :region "eu-central-1"))

(defgateway save-lunch [event ctx]
  (let [{:keys [start end]} (t/read reader (:body event))]
    (db/add-lunch! credentials start end)
    {:status  200
     :headers {:content-type (-> event :headers :content-type)}
     :body    (event :body)}))
