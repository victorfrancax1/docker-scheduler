(ns docker-scheduler.core
  (:require [amazonica.aws.dynamodbv2 :as dynamo]
            [amazonica.aws.cloudwatchevents :as events]
            [amazonica.core :refer [defcredential]]
            [clojure.data.json :as json]
            [schema.core :as s]
            [docker-scheduler.utils :as utils]
            [docker-scheduler.aws :as aws])
  (:import java.util.Date))

(def job {:image "hello-world" :date "1999-2-2T12:00"})
(def TABLE-NAME "scheduler-jobs")
(def QUEUE-ARN  "arn:aws:sqs:us-west-2:935605334497:docker-scheduler-queue")

(s/defschema NewJob
  {:image s/Str
   :date s/Str})

(s/defschema Job
  {:image s/Str
   :date s/Str
   :id s/Str})

(defn schedule-new-job! [job]
  (let [docker-job (assoc job :id (utils/uuid))]
    do
    (aws/save-job! docker-job TABLE-NAME)
    (aws/create-job-trigger! docker-job QUEUE-ARN)
    (:id docker-job)))

(defn get-all-jobs []
  (aws/query-all-jobs TABLE-NAME)) 

(defn get-single-job-details [job-id ]
  (aws/query-job-details job-id TABLE-NAME))
