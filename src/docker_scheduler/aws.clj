(ns docker-scheduler.aws
  (:require [amazonica.aws.dynamodbv2 :as dynamo]
            [amazonica.aws.cloudwatchevents :as events]
            [amazonica.core :refer [defcredential]]))

(defn save-job [docker-job table-name]
  (dynamo/put-item :table-name table-name
                   :item docker-job))

(defn create-job-event [docker-job])

(defn query-all-jobs [table-name]
  (:items (dynamo/scan :table-name table-name)))

(defn query-job-details [job-id table-name]
  (:item (dynamo/get-item :table-name table-name
                          :key {:id {:s job-id}})))
