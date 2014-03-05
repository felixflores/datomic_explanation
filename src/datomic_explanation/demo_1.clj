(ns datomic-explanation.demo-1
  (:require [datomic.api :as d]))

(def uri "datomic:mem://demo1")

(d/create-database uri)

(def connection (d/connect uri))

(def database (d/db connection))

(d/transact connection [[:db/add #db/id [:db.part/user -1] :user/first-name "felix"]])
