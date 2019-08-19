(ns docker-scheduler.core
  (:require [clj-time.core :as t ]
            [clj-time.format :as f]
            [clojure.string :as string]
            [clojure.set :as set]
            [docker-scheduler.utils :as utils]))

(def job {:image "nginx:stable" :date "2019-2-2T12:11" :id (utils/uuid)})


(defn schedule-new-job [docker-job]
  (do
    (save-job docker-job)
    (create-job-event docker-job)))


(defn save-job [docker-job])

(defn create-job-event [docker-job])

(defn query-all-jobs [])

(defn query-job-details [job-id])

