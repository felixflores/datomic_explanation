(ns datomic-explanation.demo-2
  (:require [datomic.api :as d]))

(def uri "datomic:mem://demo2")

(d/create-database uri)

(def connection (d/connect uri))

(def database (d/db connection))

(d/transact connection [[:db/add #db/id [:db.part/db -1] :db/ident :user/first-name]
                        [:db/add :db.part/db :db.install/attribute #db/id [:db.part/db -1]]])
