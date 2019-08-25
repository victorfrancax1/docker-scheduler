(ns docker-scheduler.core
  (:require [amazonica.aws.dynamodbv2 :as dynamo]
            [amazonica.aws.cloudwatchevents :as events]
            [amazonica.core :refer [defcredential]]
            [clojure.data.json :as json]
            [docker-scheduler.utils :as utils]
            [docker-scheduler.aws :as aws]))

(def job {:image "nginx:stable" :date "2019-2-2T12:11" :id (utils/uuid)})
(def TABLE-NAME "scheduler-jobs")
(def QUEUE-ARN  "arn:aws:sqs:us-west-2:935605334497:docker-scheduler-queue")

;; define sqs-queue-arn, table-name

(defn schedule-new-job! [docker-job]
  (do
    (aws/save-job! docker-job TABLE-NAME)
    (aws/create-job-trigger! docker-job QUEUE-ARN)))

(defn get-all-jobs []
  (aws/query-all-jobs TABLE-NAME)) 

(defn get-single-job-details [job-id ]
  (aws/query-job-details job-id TABLE-NAME))
