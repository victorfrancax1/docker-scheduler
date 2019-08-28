(ns docker-scheduler.utils-test
  (:require [docker-scheduler.utils :refer :all]
            [clojure.test :refer :all]))

(deftest date-to-aws-cron-test
  (testing "multiple dates"
    (is (= (date-to-aws-cron "2019-08-12T11:33") "cron(33 11 12 8 ? 2019)"))
    (is (= (date-to-aws-cron "1993-10-02T09:15") "cron(15 9 2 10 ? 1993)"))
    (is (= (date-to-aws-cron "2000-01-01T00:00") "cron(0 0 1 1 ? 2000)"))
    (is (= (date-to-aws-cron "1987-10-10T23:59") "cron(59 23 10 10 ? 1987)"))))
