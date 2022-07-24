import React from "react";
import Button from "@mui/material/Button";
import List from "@mui/material/List";
import ListItem from "@mui/material/ListItem";
import { ListItemText } from "@mui/material";
import { DataSaverOff } from "@mui/icons-material";

function ManagerMainRight() {
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
    marginRight: "100px",
  };
  const dummy_data = [
    {
      id: 1,
      name: "김상담",
      tel: "010-0000-0000",
    },
    {
      id: 2,
      name: "박상담",
      tel: "010-0000-0000",
    },
    {
      id: 3,
      name: "배상담",
      tel: "010-0000-0000",
    },
    {
      id: 4,
      name: "이상담",
      tel: "010-0000-0000",
    },
    {
      id: 5,
      name: "신상담",
      tel: "010-0000-0000",
    },
    {
      id: 6,
      name: "임상담",
      tel: "010-0000-0000",
    },
  ];
  const consultantList = dummy_data.map((data) => (
    <ListItem alignItems='center'>
      <ListItemText primary={data.id} secondary={data.name} />
    </ListItem>
  ));

  return (
    <div style={styleObj}>
      <div style={styleObj_center}>ManagerMainRight</div>
      <div style={styleObj_right}>
        <Button variant='contained'>상담사 관리</Button>
      </div>
      <div style={styleObj_center}>
        {/* 상담사 리스트 */}
        <List sx={{ width: "100%" }}>{consultantList}</List>
      </div>
    </div>
  );
}

export default ManagerMainRight;
