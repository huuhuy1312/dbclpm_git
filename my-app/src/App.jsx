import React, { useState } from 'react';
import './App.css';
import { BrowserRouter as Router, Routes, Route, Link, Navigate } from "react-router-dom";
import SearchInvoice from './pages/searchInvoice';
import ViewDetailsInvoice from './pages/viewDetailsInvoice';
import DangKySuDungNuoc from './pages/dangKySuDungNuoc';
import Login from './pages/login';
import TrangChu from './pages/trangChu';
import YeuCauDangKySuDungNuoc from './pages/yeuCauDangKySuDungNuoc';
import UpdateWaterIndex from './pages/UpdateWaterIndex';
import PageSuccess from './pages/pageSuccess';

function Logout() {
  // Handle logout logic here
    document.cookie = 'userInfo=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;';
    window.location.href="/";

  // Render nothing because redirection happens inside the function
  return null;
}

function App() {
  const [isLoggedIn, setIsLoggedIn] = useState(false);

  return (
    <Router>
      <Routes>
        <Route path="/" element={<Login />} />
        <Route path="/viewDetailsInvoice/:id" element={<ViewDetailsInvoice />} />
        <Route path="/dangKySuDungNuoc" element={<DangKySuDungNuoc />} />
        <Route path="/duyet_yeu_cau" element={<YeuCauDangKySuDungNuoc />} />
        <Route path="/trang_chu" element={<TrangChu />} />
        <Route path='/cap_nhat_so_nuoc' element={<UpdateWaterIndex />} />
        <Route path='/search_invoice' element={<SearchInvoice />} />
        <Route path='/thanhCong' element={<PageSuccess />} />
        <Route path='/logout' element={<Logout />} />
      </Routes>
    </Router>
  );
}

export default App;
