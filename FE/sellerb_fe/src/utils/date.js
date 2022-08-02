// date객체 전달받아서 변환하는 함수 
const getStringDate = (date) => {
	return date.toISOString().slice(0, 10); 
};

export default getStringDate;
