import {BasicColumn} from '/@/components/Table';
import {FormSchema} from '/@/components/Table';
import { rules} from '/@/utils/helper/validator';
import { render } from '/@/utils/common/renderUtils';
import {JVxeTypes,JVxeColumn} from '/@/components/jeecg/JVxeTable/types'
//列表数据
export const columns: BasicColumn[] = [
    {
    title: '订单编码',
    align:"center",
    dataIndex: 'orderCode'
   },
   {
    title: '下单时间',
    align:"center",
    dataIndex: 'xdDate'
   },
   {
    title: '订单总额',
    align:"center",
    dataIndex: 'money'
   },
   {
    title: '备注',
    align:"center",
    dataIndex: 'remark'
   },
];
//查询数据
export const searchFormSchema: FormSchema[] = [
];
//表单数据
export const formSchema: FormSchema[] = [
  {
    label: '订单编码',
    field: 'orderCode',
    component: 'Input',
    dynamicDisabled:true
  },
  {
    label: '下单时间',
    field: 'xdDate',
    component: 'Input',
  },
  {
    label: '订单总额',
    field: 'money',
    component: 'InputNumber',
  },
  {
    label: '备注',
    field: 'remark',
    component: 'Input',
  },
];
//子表单数据
export const cesOrderCustomerFormSchema: FormSchema[] = [
  {
    label: '客户名字',
    field: 'name',
    component: 'JPopup',
    componentProps: ({ formActionType }) => {
         const {setFieldsValue} = formActionType;
         return{
             setFieldsValue:setFieldsValue,
             code:"tj_user_report",
             fieldConfig:realname,sex,birthday,
             multi:true,
         }
     }
  },
  {
    label: '客户性别',
    field: 'sex',
    component: 'JDictSelectTag',
    componentProps:{
        dictCode:"sex"
     }
  },
  {
    label: '客户生日',
    field: 'birthday',
    component: 'DatePicker',
  },
  {
    label: '年龄',
    field: 'age',
    component: 'InputNumber',
  },
  {
    label: '常用地址',
    field: 'address',
    component: 'Input',
  },
];
//子表表格配置
export const cesOrderGoodsColumns: JVxeColumn[] = [
    {
      title: '商品名字',
      key: 'goodName',
       type: JVxeTypes.input,
      width:"200px",
      placeholder: '请输入${title}',
      defaultValue:'',
    },
    {
      title: '价格',
      key: 'price',
       type: JVxeTypes.input,
      width:"200px",
      placeholder: '请输入${title}',
      defaultValue:'',
    },
    {
      title: '数量',
      key: 'num',
       type: JVxeTypes.input,
      width:"200px",
      placeholder: '请输入${title}',
      defaultValue:'',
    },
    {
      title: '单品总价',
      key: 'zongPrice',
       type: JVxeTypes.input,
      width:"200px",
      placeholder: '请输入${title}',
      defaultValue:'',
    },
  ]
