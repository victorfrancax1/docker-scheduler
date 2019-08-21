(ns docker-scheduler.utils
  (:require [clj-time.core :as t ]
            [clj-time.format :as f]
            [clojure.string :as string]))


(defn uuid [] (str (java.util.UUID/randomUUID)))

(def formatter (f/formatters :date-hour-minute))

(defn parse-date [date] (f/parse formatter date ))

(defn date-to-aws-cron [date-string] (let [date (parse-date date-string)
                                           minute (t/minute date)
                                           hour (t/hour date)
                                           day (t/day date)
                                           month (t/month date)
                                           year (t/year date)
                                           joined-date (string/join " " [minute hour day month "?" year])]

                                (str "cron(" joined-date ")")))
