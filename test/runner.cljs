(ns lunch-lambda.runner
  (:require [doo.runner :refer-macros [doo-tests]]
          [lunch-lambda.core-test]))

(doo-tests 'lunch-lambda.core-test)
