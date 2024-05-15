import React, { useState, useEffect } from "react";
import axios from "axios";
import Address from "../model/address.ts";
import "../css/loader.css"
import "../css/dangkynuoc.css";
import Success from "./components/success.jsx";
const DangKySuDungNuoc = () => {
  const [cities, setCities] = useState([]);
  const [districts, setDistricts] = useState([]);
  const [wards, setWards] = useState([]);
  const [selectedCity, setSelectedCity] = useState("");
  const [selectedDistrict, setSelectedDistrict] = useState("");
  const [selectedWard, setSelectedWard] = useState("");
  const [detailsAddress, setDetailsAddress] = useState("");
  const [fullName, setFullName] = useState("");
  const [email, setEmail] = useState("");
  const [phoneNumber, setPhoneNumber] = useState("");
  const [numId, setNumId] = useState("");
  const [imageFront, setImageFront] = useState("");
  const [imageBack, setImageBack] = useState("");
  const [certificationOfProvety, setCertificationOfProvety] = useState(null);
  const [listResidentalType, setListResidentalType] = useState([]);
  const [emailError, setEmailError] = useState("");
  const [phoneNumberError, setPhoneNumberError] = useState("");
  const [isCMTValid, setIsCMTValid] = useState(false);
  const [errorTotal,setErrorTotal] = useState("");
  const [success, setSuccess] = useState("");
  // Hàm kiểm tra định dạng số điện thoại
  const validatePhoneNumber = (phoneNumber) => {
    const regex = /^[0-9]{10}$/; // Định dạng: 10 chữ số
    return regex.test(phoneNumber);
  };
  const handleImageChange = (event, setImageFunction) => {
  
    const file = event.target.files[0];
    const reader = new FileReader();
    reader.onloadend = () => {
      setImageFunction(reader.result);
    };
    if (file) {
      reader.readAsDataURL(file);
    }
  };
  const getAllResidentalType = async () => {
    try {
      const response = await fetch(
        "http://127.0.0.1:8080/api/residentialType/all",
        {
          method: "GET",
        }
      );

      if (!response.ok) {
        throw new Error("Network response was not ok");
      }
      const data = await response.json();
      console.log(data);
      setListResidentalType(data);
    } catch (error) {
      console.error(error);
      throw error;
    }
  };
  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await axios.get(
          "https://raw.githubusercontent.com/kenzouno1/DiaGioiHanhChinhVN/master/data.json"
        );
        setCities(response.data);
      } catch (error) {
        console.error("Error fetching data:", error);
      }
    };
    getAllResidentalType();
    fetchData();
  }, []);
  const [selectedOption, setSelectedOption] = useState(1);
  const handleRegister = async () => {
    if(isCMTValid===null && emailError===null && phoneNumberError===null && selectedCity!=="" &&selectedDistrict!==""&& selectedWard!=="" && detailsAddress!==""&& fullName!==""&&imageFront!==""&& imageBack!=="" )
    {
        const requestBody = {
          email: email,
          phoneNumber: phoneNumber,
          name: fullName,
          baseId: 1,
          residentialTypeId: selectedOption,
          provinceOrCity: selectedCity,
          district: selectedDistrict,
          wards: selectedWard,
          detailsAddress: detailsAddress,
          front_image: imageFront,
          back_image: imageBack,
          certificate_of_poverty: certificationOfProvety,
        };
  
      console.log("Request Body:", requestBody); // In ra nội dung của body trước khi gửi yêu cầu API
        setSuccess(`Đã gửi yêu cầu đăng ký sử dụng nước thành công. \nVui lòng kiểm tra email trong thời gian tới để biết kết quả!!!!`)
      try {
        const response = await axios.post(
          "http://127.0.0.1:8080/api/customer/add",
          requestBody
        );
  
        console.log(response.data);
        setErrorTotal(null);
      } catch (error) {
        console.error("Lỗi:", error);
        // Xử lý lỗi ở đây nếu cần
      }
    } 
    else{
      setErrorTotal("Xin vui lòng nhập đầy đủ thông tin!!!");
    }
  };
  // Hàm xử lý sự kiện khi lựa chọn thay đổi
  const handleOptionChange = (event) => {
    setSelectedOption(event.target.value);
  };
  const handleCityChange = (e) => {
    const selectedCityName = e.target.value;
    const selectedCity = cities.find((city) => city.Name === selectedCityName);
    setSelectedCity(selectedCityName);
    setSelectedDistrict("");
    setSelectedWard("");
    setDistricts(selectedCity ? selectedCity.Districts : []);
    setWards([]);
  };

  // Trong phần handleDistrictChange:
  const handleDistrictChange = (e) => {
    const selectedDistrictName = e.target.value;
    const selectedDistrict = districts.find(
      (district) => district.Name === selectedDistrictName
    );
    setSelectedDistrict(selectedDistrictName);
    setSelectedWard("");
    setWards(selectedDistrict ? selectedDistrict.Wards : []);
  };

  // Trong phần handleWardChange:
  const handleWardChange = (e) => {
    setSelectedWard(e.target.value);
  };
  const handleDetailsAddress = (e) => {
    setDetailsAddress(e.target.value);
  };
  const handleFullNameChange = (e) => {
    setFullName(e.target.value);
  };
  const validateEmail = (email) => {
    const regex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return regex.test(email);
  };
  const handleEmailChange = (e) => {
    setEmail(e.target.value);
    if(!validateEmail(e.target.value))
    {
      setEmailError("Địa chỉ email không hợp lệ.")
    }else{
      setEmailError(null)
    }
  };
  const handlePhoneNumberChange = (e) => {
    setPhoneNumber(e.target.value);
    if(!validatePhoneNumber(e.target.value))
    {
      setPhoneNumberError("Số điện thoại không hợp lệ.")
    }else{
      setPhoneNumberError(null)
    }
  };
  const handleNumIdChange = (e) => {
    const regexPattern = /^\d{9}$|^\d{12}$/;
    setNumId(e.target.value);
    if(regexPattern.test(e.target.value))
    {
      setIsCMTValid(null)
    }
    else{
      setIsCMTValid("Số CCCD/CMT không hợp lệ")
    }
  };
  
  return (
    <div className="root-register">
      <div className="form-regis">
        <div className="title">Nhập thông tin đăng ký sử dụng nước</div>
        <div className="form-input-regis">
          <div className="form-left">
            <div>
              <div>Chọn tỉnh/thành phố</div>
              <select
                className="form-select form-select-sm mb-3"
                id="city"
                aria-label=".form-select-sm"
                onChange={handleCityChange}
                value={selectedCity}
              >
                <option value="" disabled>
                  Chọn tỉnh thành
                </option>
                {cities.map((city) => (
                  <option key={city.Id} value={city.Name}>
                    {city.Name}
                  </option>
                ))}
              </select>
            </div>
            <div>
              <div>Chọn quận huyện</div>
              <select
                className="form-select form-select-sm mb-3"
                id="district"
                aria-label=".form-select-sm"
                onChange={handleDistrictChange}
                value={selectedDistrict}
              >
                <option value="" disabled>
                  Chọn quận huyện
                </option>
                {districts.map((district) => (
                  <option key={district.Id} value={district.Name}>
                    {district.Name}
                  </option>
                ))}
              </select>
            </div>
            <div>
              <div>Chọn phường xã</div>
              <select
                className="form-select form-select-sm"
                id="ward"
                aria-label=".form-select-sm"
                onChange={handleWardChange}
                value={selectedWard}
              >
                <option value="" disabled>
                  Chọn phường xã
                </option>
                {wards.map((ward) => (
                  <option key={ward.Id} value={ward.Name}>
                    {ward.Name}
                  </option>
                ))}
              </select>
            </div>
            <div>
              <div>Nhập địa chỉ cụ thể:</div>
              <input
                value={detailsAddress}
                onChange={(e) => handleDetailsAddress(e)}
                placeholder="Nhập địa chỉ cụ thể"
              ></input>
            </div>
          </div>
          <div className="form-right">
            <div>
              <div>Họ và tên:</div>
              <input
                placeholder="Nhập họ và tên"
                onChange={(e) => handleFullNameChange(e)}
                value={fullName}
              ></input>
            </div>
            <div>
              <div>Email</div>
              <input
                placeholder="Nhập email"
                onChange={(e) => handleEmailChange(e)}
                value={email}
                type="email"
              ></input>
              {emailError && <div style={{ color: "red" }}>{emailError}</div>}
            </div>
            <div>
              <div>Số điện thoại</div>
              <input
                placeholder="Nhập số điện thoại"
                onChange={(e) => handlePhoneNumberChange(e)}
                value={phoneNumber}
                type="number"
              ></input>
              {phoneNumberError && <div style={{ color: "red" }}>{phoneNumberError}</div>}
            </div>
            <div>
              <div>Nhập số CCCD/CMT</div>
              <input
                type="number"
                placeholder="Nhập số CCCD/CMT"
                onChange={(e) => handleNumIdChange(e)}
                value={numId}
              ></input>
              {isCMTValid && <div style={{ color: "red" }}>{isCMTValid}</div>}
            </div>
          </div>
        </div>
        <div className="img-cccd">
          <div className="mb-3">
            <label htmlFor="frontImage" className="form-label">
              Ảnh CCCD/CMT mặt trước:
            </label>
            <div style={{ width: 400, height: 200 }}>
              {imageFront && (
                <img
                  style={{
                    width: "100%",
                    height: "100%",
                    objectFit: "contain",
                  }}
                  src={imageFront}
                  alt="Ảnh CCCD/CMT mặt sau"
                />
              )}
            </div>
            <input
              type="file"
              className="form-control"
              id="frontImage"
              accept="image/*"
              onChange={(e) => handleImageChange(e, setImageFront)}
            />
          </div>
          <div className="mb-3">
            <label htmlFor="backImage" className="form-label">
              Ảnh CCCD/CMT mặt sau:
            </label>
            <div style={{ width: 400, height: 200 }}>
              {imageBack && (
                <img
                  style={{
                    width: "100%",
                    height: "100%",
                    objectFit: "contain",
                  }}
                  src={imageBack}
                  alt="Ảnh CCCD/CMT mặt sau"
                />
              )}
            </div>
            <input
              type="file"
              className="form-control"
              id="backImage"
              accept="image/*"
              onChange={(e) => handleImageChange(e, setImageBack)}
            />
          </div>
        </div>
        <div className="img-cn">
          <div>
            <label htmlFor="choices">Chọn một lựa chọn:</label>
            <select
              id="choices"
              value={selectedOption}
              onChange={handleOptionChange}
            >
              {listResidentalType.map((item) => {
                return <option value={item.id}>{item.name}</option>;
              })}
            </select>
          </div>
          {selectedOption == 2 ? (
            <div className="mb-3" >
              <label htmlFor="certificationOfProvety" className="form-label">
                Giấy chứng nhận hộ nghèo, hộ cận nghèo:
              </label>
              <div style={{ width: 100, height: 100 }}>
                { certificationOfProvety&& (
                  <img
                    style={{
                      width: "100%",
                      height: "100%",
                      objectFit: "contain",
                    }}
                    src={certificationOfProvety}
                    alt="Giấy chứng nhận hộ nghèo, cận nghèo"
                  />
                )}
              </div>
              <input
                type="file"
                className="form-control"
                id="certificationOfProvety"
                accept="image/*"
                onChange={(e) =>
                  handleImageChange(e, setCertificationOfProvety)
                }
              />
            </div>
          ) : null}
        </div>
        {errorTotal && <div style={{ color: "red", textAlign:"center", marginTop:10, fontSize:20 }}>{errorTotal}</div>}
        <button onClick={handleRegister} className="bt-dk">
          Đăng ký sử dụng nước
        </button>
      </div>
      
      {success&& <Success content={`Đã gửi yêu cầu đăng ký sử dụng nước thành công. \nVui lòng kiểm tra email trong thời gian tới để biết kết quả!!!!`}/>}
    </div>
  );
};

export default DangKySuDungNuoc;
