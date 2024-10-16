package rs.raf.rma.catalist.breeds.api.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CatsApiModel (
    val weight: Weight? = Weight("",""),
    @SerialName("id")val catId: String = "",
    @SerialName("name")val breed: String = "",
    @SerialName("cfa_url") val cfaUrl: String? = null,
    @SerialName("vetstreet_url") val vetStreetUrl: String= "",
    @SerialName("vcahospitals_url") val vcaHospitalsUrl: String? = null,
    val temperament: String= "",
    val origin: String= "",
    @SerialName("country_codes")val countryCodes: String= "",
    @SerialName("country_code")val countryCode: String= "",
    val description: String= "",
    @SerialName("life_span")val lifeSpan: String= "",
    val indoor: Int= 0,
    val lap: Int? = null,
    @SerialName("alt_names")val altNames: String? = "",
    val adaptability: Int= 0,
    @SerialName("affection_level")val affectionLevel: Int= 0,
    @SerialName("child_friendly")val childFriendly: Int= 0,
    @SerialName("dog_friendly")val dogFriendly: Int= 0,
    @SerialName("energy_level")val energyLevel: Int= 0,
    val grooming: Int= 0,
    @SerialName("health_issues")val healthIssues: Int= 0,
    val intelligence: Int= 0,
    @SerialName("shedding_level")val sheddingLevel: Int= 0,
    @SerialName("social_needs")val socialNeeds: Int= 0,
    @SerialName("stranger_friendly")val strangerFriendly: Int= 0,
    val vocalisation: Int= 0,
    val experimental: Int= 0,
    val hairless: Int= 0,
    val natural: Int= 0,
    val rare: Int= 0,
    val rex: Int= 0,
    @SerialName("suppressed_tail")val suppresedTail: Int= 0,
    @SerialName("short_legs")val shortLegs: Int= 0,
    @SerialName("wikipedia_url")val wikipediaUrl: String= "",
    val hypoallergenic: Int= 0,
   @SerialName("reference_image_id") val referenceImageId: String= "",
    val image: Image? = Image("",0,0,""),
)

@Serializable
data class Weight(
    val imperial: String= "",
    val metric: String= ""
)
@Serializable
data class Image(
    @SerialName("id")val imageId: String= "",
    val width: Int= 0,
    val height: Int= 0,
    val url: String= "",
)