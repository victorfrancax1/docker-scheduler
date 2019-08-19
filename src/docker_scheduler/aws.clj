(ns docker-scheduler.aws
  (:require [amazonica.aws.dynamodbv2 :as dynamo]))

(defn save-job [docker-job table-name endpoint]
  (let[cred {:endpoint endpoint}]
    (dynamo/put-item cred
                     :table-name table-name
                     :item docker-job)))

(defn create-job-event [docker-job])

(defn query-all-jobs [])

(defn query-job-details [job-id])
