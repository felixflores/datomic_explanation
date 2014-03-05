(ns datomic-explanation.slide11
  (:require [datomic.api :as d]))

(def uri "datomic:mem://example")

(d/create-database uri)

(def connection (d/connect uri))

(def database (d/db connection))

;; (d/transact connection [[:db/add #db/id [:db.part/user] :user/first-name "felix"]])
;;
;; (d/transact connection [[:db/add #db/id [:db.part/db -1] :db/ident :user/first-name]
;;                         [:db/add :db.part/db :db.install/attribute #db/id [:db.part/db -1]]])
;;
;; (d/transact connection [[:db/add #db/id [:db.part/db -1] :db/ident :user/first-name]
;;                         [:db/add #db/id [:db.part/db -1] :db/cardinality :db.cardinality/one]
;;                         [:db/add #db/id [:db.part/db -1] :db/valueType :db.type/string]
;;                         [:db/add :db.part/db :db.install/attribute #db/id [:db.part/db -1]]])
;;
;; (d/transact connection [{:db/id #db/id[:db.part/db]
;;                          :db/ident :user/last-name
;;                          :db/valueType :db.type/string
;;                          :db/cardinality :db.cardinality/one
;;                          :db.install/_attribute :db.part/db}])
;;
;; (d/transact connection [{:db/id #db/id[:db.part/db]
;;                          :db/ident :user/email
;;                          :db/valueType :db.type/string
;;                          :db/cardinality :db.cardinality/many
;;                          :db.install/_attribute :db.part/db}
;;
;;                         {:db/id #db/id[:db.part/db]
;;                          :db/ident :user/twitter
;;                          :db/valueType :db.type/string
;;                          :db/cardinality :db.cardinality/one
;;                          :db/unique :db.unique/identity
;;                          :db.install/_attribute :db.part/db}])


(def project-dir (System/getProperty "user.dir"))

(def db-schema "src/datomic_explanation/slide11.edn")

(def schema (->> (str project-dir "/" db-schema)
                 slurp
                 read-string))

(d/transact connection schema)

(d/transact connection [[:db/add #db/id [:db.part/user -1] :user/first-name "felix"]])

(d/transact connection [{:db/id #db/id [:db.part/user -1]
                         :user/last-name "flores"
                         :user/email "felixflores@gmail.com"
                         :user/twitter "felixflores"
                         :user/homepage "http://felixflor.es"}])

(d/q '[:find ?e
       :where
       [?e :user/first-name "felix"]]
     database)

(def database-now (d/db connection))

;; (d/q '[:find ?attr ?name
;;        :where
;;        [?attr :db/ident ?name]
;;        [_ :db.install/attribute ?attr]]
;;
;;      database)
;;
;; (d/q '[:find ?e
;;        :where
;;        [?e :db/ident]]
;;      database)
;;
;;
;; (d/q '[:find ?attr ?name
;;        :where
;;        [?attr :db/ident ?name]
;;        [_ :db.install/attribute ?attr]]
;;      database)
;;
;; (def database1 (d/db connection))
;;
;; (d/q '[:find ?attr ?name
;;        :where
;;        [?attr :db/ident ?name]
;;        [_ :db.install/attribute ?attr]]
;;      database1)
;;
;; (d/q '[:find ?attr
;;        :where
;;        [?attr :db/ident :user/email]
;;        [_ :db.install/attribute ?attr]]
;;      database1)
;;
;; (d/q '[:find ?attr
;;        :where
;;        [?attr :db/cardinality]
;;        [?attr :db/ident :user/email]
;;        [?attr :db/valueType :db.type/string]
;;        [_ :db.install/attribute ?attr]]
;;      database1)
;;
;; (keyword :this.is/a-keyword)
;; (name (keyword :this.is/a-keyword))
;; (namespace (keyword :this.is/a-keyword))
;;
;; (defn datomic-attribute?
;;   [identifier]
;;   (not-empty (re-matches #"(db|fressian).*" (namespace identifier))))
;;
;; (->> (d/q '[:find ?attr ?name
;;        :where
;;        [?attr :db/ident ?name]
;;        [_ :db.install/attribute ?attr]]
;;      database1)
;;      (remove #(datomic-attribute? (last %))))
;;
;;
;; (d/q '[:find ?e
;;        :where
;;        [?attr :db/ident :user/email]
;;        [?e :db.install/attribute ?attr]]
;;      database1)
;;
;; (def installed (d/entity database1 0))
;;
;; (d/touch installed)
;;
;; {:db/ident :db.part/db
;;  :db.install/partition #{:db.part/tx :db.part/db :db.part/user}
;;  :db.install/valueType #{:db.type/uuid :db.type/bigint :db.type/uri :db.type/ref :db.type/keyword :db.type/bytes :db.type/string :db.type/instant :db.type/fn :db.type/long :db.type/bigdec :db.type/boolean :db.type/double :db.type/float}
;;  :db.install/attribute #{:db/noHistory :db.excise/before :user/first-name :user/homepage :user/twitter :db.excise/beforeT :db.install/partition :db/cardinality :user/email :db.install/attribute :db/index :db/excise :db/unique :db/fulltext :db.alter/attribute :db/txInstant :db/lang :db/doc :db.install/valueType :db.excise/attrs :user/last-name :db/code :db/isComponent :db/fn :db.install/function :db/valueType :db/ident :bank/account-number :fressian/tag}
;;  :db.install/function #{:db.fn/cas :db.fn/retractEntity}
;;  :db/doc "Name of the system partition. The system partition includes the core of datomic, as well as user schemas: type definitions, attribute definitions, partition definitions, and data function definitions."
;;  :db/id 0}

;;
(d/delete-database uri)
