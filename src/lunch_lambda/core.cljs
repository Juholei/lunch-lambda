(ns lunch-lambda.core
  (:require [cljs-lambda.macros :refer-macros [defgateway]]
            [eulalie.creds :as creds]
            [eulalie.util :as eu]
            [cognitect.transit :as t]
            [lunch-lambda.dynamo :as db]))

(def reader (t/reader :json))
(def credentials (assoc (creds/env) :region "eu-central-1"))
(def secret-key "LUNCH_SECRET")

(defn save [credentials event body secret]
  (let [{:keys [start end request-secret]} body]
    (if (= request-secret secret)
      (do
        (db/add-lunch! credentials start end)
        {:status  200
         :headers {:content-type (-> event :headers :content-type)}
         :body    (:body event)})
      {:status 403})))

(defgateway save-lunch [event ctx]
  (save credentials event (t/read reader (:body event)) (eu/env! secret-key)))
