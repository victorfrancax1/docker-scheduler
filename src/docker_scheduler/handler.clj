(ns docker-scheduler.handler
  (:require [compojure.api.sweet :refer :all]
            [ring.util.http-response :refer :all]
            [schema.core :as s]
            [docker-scheduler.core :as core]))

(def app
  (api
    {:swagger
     {:ui "/"
      :spec "/swagger.json"
      :data {:info {:title "Docker Scheduler API"
                    :description "Docker scheduler API in AWS!"}
             :tags [{:name "scheduler", :description "docker scheduler!"}]}}}

    (context "/scheduler" []
      :tags ["scheduler"]

      (GET "/jobs" []
        :return [core/Job]
        :summary "Gets all scheduled jobs"
        (ok (core/get-all-jobs)))

      (POST "/jobs" []
        :return {:id s/Uuid}
        :body [job core/NewJob]
        :summary "Schedule a new job"
        (let [id (core/schedule-new-job! job)]
          (created (str "/jobs/" id) {:id id})))

      (GET "/jobs/:id" []
        :return core/Job
        :path-params [id :- s/Uuid]
        :summary "Gets details of a job"
        (let [response (core/get-single-job-details id)]
          (if (nil? response)
              (not-found)
              (ok response)))))))
