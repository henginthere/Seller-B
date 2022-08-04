import React, { useState , useEffect} from 'react'
import { Link, useNavigate} from "react-router-dom";

import Axios from 'axios'
import "./ProductList.css"
import { Footer, NavBar, ProdcutOption } from "../../../components/index";
import { productLineListApi} from '../../../api/productApi'

function ProductList() {
   
    const navigate = useNavigate();

    const [optionList, setOptionList] = useState([]);
    const [searchWord, setSearchWord] = useState("");
    const [groupOption, setGroupOption] = useState("");

    const onHandleGroupOption = (event)=>{
        setGroupOption(event.currentTarget.value);
    
        // console.log(groupOption);
      }

    const onSearchWordHandler = (e)=>{
        setSearchWord(e.target.value);
        console.log(searchWord)
    }

    const onSearchBtn = () => {
        //
    }

    const onRegisterBtn = ()=>{
        navigate('/manager/productRegister')
    }

    // 선택한 제품군 option에 따라, 나타낼 해당 제품군 리스트 컴포넌트
    function GroupOptionList( ){
        return (
            <ProdcutOption group = {groupOption} />
        );
    }

    useEffect(() => {
        productLineListApi()
          .then((res) => {
            // 1) 현재 로그인한 관리자가 속한 brand 이름 알아옴
            // 2) 전체 LineList를 돌면서, 해당 정보 찾기
            // 3) 해당하는 브랜드 - productGroups 를 map으로 돌면서 제품그룹군 알아오기
            
            console.log(res.data[0].brandGroups);
            setOptionList(res.data.brandGroups);

          })
          .catch((err) => {
            console.log(err);
          });
      }, []);

  return (
    <>
        <NavBar />
            <div className="page-title">제품 목록</div>
            <div className="product-list">
                <div className='page-navi-wrapper'>
                    <div className='navi-left'>
                        <select onChange={onHandleGroupOption} value={groupOption}>
                            {
                                optionList.map((ele, i)=>{  
                                    return (
                                    <option>{ele}</option>
                                    )
                                })
                            }
                            <option>에어컨</option>
                            <option>냉장고</option>
                            <option>전자레인지</option>
                        </select>
                    </div>
                    <div className='navi-right'>
                        <input 
                            placeholder='제품검색하기'  
                            value={searchWord}
                            onChange={onSearchWordHandler}
                        />
                        <button>go</button>
                        
                        <Link to="/manager/productRegister">
                        <div >
                            제품등록
                        </div>
                        </Link>

                    </div>
                </div>
                <div className='product-list-wrapper'>
                    <GroupOptionList />
                </div>
                {/* end : page-navi-wrapper */}
            </div>
        <Footer />
    </>
  )
}


export default ProductList