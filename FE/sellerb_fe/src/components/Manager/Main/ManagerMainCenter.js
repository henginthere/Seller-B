import React, { useEffect, useState } from "react";
import Button from "@mui/material/Button";
import List from "@mui/material/List";
import ListItem from "@mui/material/ListItem";
import { ListItemText } from "@mui/material";
import { Divider } from "@mui/material";
import { Link, useNavigate } from "react-router-dom";
import { listConsultantApi } from '../../../api/consultantApi'

const styleObj = {
    width: "75%",
    border: "solid 2px black",
    borderRadius: "10px",
    margin: "10px",
  };
  const styleObj_center = {
    textAlign: "center",
    margin: "50px",
  };
  const styleObj_right = {
    display: "flex",
    justifyContent: "flex-end",
    margin: "50px",
  };
  
  const tableHeader = {
    display: "flex",
    // textAlign: "center",
    // justifyContent: "center",
    backgroundColor: "grey",
  }
  
  const tableRow = {
    
  }
  
  const tableData = {
    justifyContent: "start"
  }


function ManagerMainCenter() {
  return (
    <div style={styleObj}>
      <div style={styleObj_right}>
        <Link to="/manager/consultantRegister">
          <Button variant="contained">제품군 추가</Button>
        </Link>
      </div>
      <div style={styleObj_center}>

      </div>
    </div>
  )
}

export default ManagerMainCenter