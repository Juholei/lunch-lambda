(ns lunch-lambda.dynamo
  (:require [hildebrand.core :as hc]))

(defn add-lunch! [creds start end]
  (hc/put-item! creds
                :lunchTable
                {:id (str (.getTime (js/Date.)))
                 :start start
                 :end end}))
