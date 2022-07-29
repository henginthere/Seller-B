import React, { useState, useEffect } from "react";
import { Link, useNavigate } from "react-router-dom";

import Axios from "axios";
import "./ProductRegister.css";
import { Footer, NavBar } from "../../../components/index";
import { productRegisterApi } from "../../../api/productApi";
import axios from "axios";

function ProductDetail() {
  const [product, setProduct] = useState({
    productId: "",
    productName: "",
    productPrice: "",
    productLine: "",
    formData: "",
  });



  return (
    <>
      <NavBar />
      <h4 className="page-title">제품 등록</h4>

      <Footer />
    </>
  );
}

export default ProductDetail;
