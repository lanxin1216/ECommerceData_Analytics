declare namespace API {
  type BaseResponseListMapStringObject_ = {
    code?: number
    data?: MapStringObject_[]
    message?: string
  }

  type BaseResponseMapStringObject_ = {
    code?: number
    data?: Record<string, any>
    message?: string
  }

  type BaseResponseObject_ = {
    code?: number
    data?: Record<string, any>
    message?: string
  }

  type BaseResponseString_ = {
    code?: number
    data?: string
    message?: string
  }

  type getOrdersByMonthUsingGETParams = {
    /** month */
    month: string
  }

  type getOrderTrendUsingGETParams = {
    /** type */
    type: string
  }

  type getUsersUsingGETParams = {
    /** page */
    page: number
    /** pageSize */
    pageSize: number
  }

  type MapStringObject_ = true

  type MapStringObject_1 = true

  type MapStringObject_10 = true

  type MapStringObject_2 = true

  type MapStringObject_3 = true

  type MapStringObject_4 = true

  type MapStringObject_5 = true

  type MapStringObject_6 = true

  type MapStringObject_7 = true

  type MapStringObject_8 = true

  type MapStringObject_9 = true
}
