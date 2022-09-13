package ru.surfeducation.utils.mappers

interface EntityMapper<IncomingEntity, OutComingEntity> {

    fun mapFromIncomingEntity(entity: IncomingEntity): OutComingEntity

    fun mapFromOutComingEntity(domainModel: OutComingEntity): IncomingEntity

}