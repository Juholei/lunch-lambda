(defproject lunch-lambda "0.1.0-SNAPSHOT"
  :dependencies [[org.clojure/clojure       "1.8.0"]
                 [org.clojure/clojurescript "1.8.51"]
                 [org.clojure/core.async "0.3.465"]
                 [com.cognitect/transit-cljs "0.8.243"]
                 [io.nervous/cljs-lambda    "0.3.5"]
                 [io.nervous/eulalie "0.6.10"]
                 [io.nervous/hildebrand "0.4.5"]]

  :plugins [[lein-npm                    "0.6.2"]
            [io.nervous/lein-cljs-lambda "0.6.6"]]
  :npm {:dependencies [[serverless-cljs-plugin "0.1.2"]]}
  :cljs-lambda {:compiler
                {:inputs  ["src"]
                 :options {:output-to     "target/lunch-lambda/lunch_lambda.js"
                           :output-dir    "target/lunch-lambda"
                           :target        :nodejs
                           :language-in   :ecmascript5
                           :optimizations :none}}})
