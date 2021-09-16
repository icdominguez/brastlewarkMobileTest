package com.example.brastlewarkmobiletest.domain

import com.example.brastlewarkmobiletest.domain.Inhabitant
import com.google.gson.annotations.SerializedName

data class GetInhabitantsResponse (
    @SerializedName("Brastlewark")
    var inhabitants : ArrayList<Inhabitant>
)