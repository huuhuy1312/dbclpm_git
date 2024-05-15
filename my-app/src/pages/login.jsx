//import cacs thuw vieenj sau:
import "../css/login.css";
import { CiUser } from "react-icons/ci";
import { CiLock } from "react-icons/ci";
import { Link } from "react-router-dom";
import axios from "axios";
import React, { useState } from "react";
const Login = () => {
  //click vào mấy cái thanh input
  const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [error, setError] = useState("");

  const [isFocusedN, setIsFocusedN] = useState(false);
  const [isFocusedMK, setIsFocusedMK] = useState(false);
  const [iconUser, setIconUser] = useState("black");
  const [iconPass, setIconPass] = useState("black");
  const handleFocusN = () => {
    setIsFocusedN(true);
    setIconUser("rebeccapurple");
  };

  const handleBlurN = () => {
    setIsFocusedN(false);
    setIconUser("black");
  };

  const handleFocusMK = () => {
    setIsFocusedMK(true);
    setIconPass("rebeccapurple");
  };

  const handleBlurMK = () => {
    setIsFocusedMK(false);
    setIconPass("black");
  };
  const handleLogin = async () => {
    if(username==="")
    {
        setError("Xin vui lòng nhập tên đăng nhập")
    }
    else{
        if(password==="")
        {
            setError("Xin vui lòng nhập password");
        }
        else{
            try {
                const response = await axios.post("http://127.0.0.1:8080/api/auth/login", {
                    username: username,
                    password: password
                });
                if (response.status === 200) {
                    document.cookie = `userInfo=${JSON.stringify(response.data)}; expires=Thu, 18 Dec 2025 12:00:00 UTC; path=/`;
                    window.location.href = '/trang_chu';
                }
            } catch (error) {
                console.error('Lỗi:', error);
                setError("Tên đăng nhập hoặc mật khẩu không chính xác");
            }
        }
    }
    
}
const handleKeyPress=(e)=>{
    if(e.key === "Enter")
    {
        handleLogin();
    }
}
  return (
    <div className="root-login">
      <div className="form-dk">
        <div className="title">Đăng nhập</div>
        <div className="form-input">
          <div className="form-input-div">
            <span style={{ color: iconUser }}>
              <CiUser />
            </span>
            <input
              placeholder={isFocusedN ? "" : "Nhập vào đây"}
              onFocus={handleFocusN}
              onBlur={handleBlurN}
              onChange={(e) => setUsername(e.target.value)} value={username} onKeyDown={handleKeyPress}
              type="text"
            ></input>
          </div>
          <div className="form-input-div">
            <span style={{ color: iconPass }}>
              <CiLock />
            </span>
            <input
              placeholder={isFocusedMK ? "" : "Nhập vào đây"}
              onFocus={handleFocusMK}
              onBlur={handleBlurMK}
              onChange={(e) => setPassword(e.target.value)} value={password} onKeyDown={handleKeyPress}
              type="password"
            ></input>
          </div>
        </div>
        
        {error && <div style={{ textAlign:"center",color: 'red' }}>{error}</div>}
        <button className="bt-dk" onClick={handleLogin}>Xác Nhận</button>
        <div className="dk-nuoc">
          <Link to={"/dangKySuDungNuoc"} className="dk-nuoc-link">
            Đăng ký sử dụng nước
          </Link>
        </div>
      </div>
    </div>
  );
};
export default Login;
