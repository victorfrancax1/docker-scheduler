(ns docker-scheduler.aws
  (:require [amazonica.aws.dynamodbv2 :as dynamo]
            [amazonica.aws.cloudwatchevents :as events]
            [amazonica.core :refer [defcredential]]
            [docker-scheduler.utils :refer [date-to-aws-cron]]))

(defn save-job [docker-job table-name]
  (dynamo/put-item :table-name table-name
                   :item docker-job))

(defn create-job-event-rule [docker-job]
  (let [cron (date-to-aws-cron (:date docker-job))
        rule-arn (events/put-rule
                  :name  (:id docker-job)
                  :description "Docker-based job!"
                  :schedule-expression cron)]
    (:rule-arn rule-arn)))

(defn put-job-event-target [docker-job queue-arn]
  (events/put-targets
   :rule (:id docker-job)
   :targets [{:id    "docker-scheduler-queue"
              :arn   queue-arn
              :input (json/write-str docker-job)}]))

(defn query-all-jobs [table-name]
  (:items (dynamo/scan :table-name table-name)))

(defn query-job-details [job-id table-name]
  (:item (dynamo/get-item :table-name table-name
                          :key {:id {:s job-id}})))
