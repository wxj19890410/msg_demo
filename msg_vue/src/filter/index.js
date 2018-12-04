const setMonth = function (dates) {
   	const params = {}
   	if (null != dates && dates.length > 0) {
   		let startDate = dates[0]
	    let endDate = dates[1]
	    params.startMonth = startDate.getFullYear() + "-" + (startDate.getMonth() + 1)
	    params.monthNub = endDate.getMonth() - startDate.getMonth() + (startDate.getFullYear() - endDate.getFullYear())*12
   	}
   	return params
}


export default {
  setMonth
}