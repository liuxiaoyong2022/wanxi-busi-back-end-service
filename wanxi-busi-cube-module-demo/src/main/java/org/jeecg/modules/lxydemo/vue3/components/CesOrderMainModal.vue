<template>
  <BasicModal v-bind="$attrs" @register="registerModal" :title="title" @ok="handleSubmit">
      <BasicForm @register="registerForm" ref="formRef"/>
  <!-- 子表单区域 -->
    <a-tabs v-model:activeKey="activeKey" @change="handleChangeTabs">
      <a-tab-pane tab="订单商品" :key="refKeys[0]" :forceRender="true">
        <JVxeTable
          keep-source
          resizable
          :ref="refKeys[0]"
          :loading="cesOrderGoodsTable.loading"
          :columns="cesOrderGoodsTable.columns"
          :dataSource="cesOrderGoodsTable.dataSource"
          :maxHeight="300"
          :rowNumber="true"
          :rowSelection="true"
          :toolbar="true"
          />
      </a-tab-pane>
      <a-tab-pane tab="订单客户" :key="refKeys[1]" :forceRender="true">
        <CesOrderCustomerForm ref="cesOrderCustomerForm"></CesOrderCustomerForm>
      </a-tab-pane>

    </a-tabs>
  </BasicModal>
</template>

<script lang="ts" setup>
    import {ref, computed, unref,reactive} from 'vue';
    import {BasicModal, useModalInner} from '/@/components/Modal';
    import {BasicForm, useForm} from '/@/components/Form/index';
    import { JVxeTable } from '/@/components/jeecg/JVxeTable'
    import { useJvxeMethod } from '/@/hooks/system/useJvxeMethods.ts'
    import CesOrderCustomerForm from './CesOrderCustomerForm.vue'
    import {formSchema,cesOrderGoodsColumns} from '../cesOrderMain.data';
    import {saveOrUpdate,cesOrderGoodsList,cesOrderCustomerList} from '../cesOrderMain.api';
    import { VALIDATE_FAILED } from '/@/utils/common/vxeUtils'
    // Emits声明
    const emit = defineEmits(['register','success']);
    const isUpdate = ref(true);
    const refKeys = ref(['cesOrderGoods', 'cesOrderCustomer', ]);
    const activeKey = ref('cesOrderGoods');
    const cesOrderGoods = ref();
    const cesOrderCustomerForm = ref();
    const tableRefs = {cesOrderGoods, };
    const cesOrderGoodsTable = reactive({
          loading: false,
          dataSource: [],
          columns:cesOrderGoodsColumns
    })
    //表单配置
    const [registerForm, {setProps,resetFields, setFieldsValue, validate}] = useForm({
        labelWidth: 150,
        schemas: formSchema,
        showActionButtonGroup: false,
    });
     //表单赋值
    const [registerModal, {setModalProps, closeModal}] = useModalInner(async (data) => {
        //重置表单
        await reset();
        setModalProps({confirmLoading: false,showCancelBtn:data?.showFooter,showOkBtn:data?.showFooter});
        isUpdate.value = !!data?.isUpdate;
        if (unref(isUpdate)) {
            //表单赋值
            await setFieldsValue({
                ...data.record,
            });
             cesOrderCustomerForm.value.initFormData(cesOrderCustomerList,data?.record?.id)
             requestSubTableData(cesOrderGoodsList, {id:data?.record?.id}, cesOrderGoodsTable)
        }
        // 隐藏底部时禁用整个表单
       setProps({ disabled: !data?.showFooter })
    });
    //方法配置
    const [handleChangeTabs,handleSubmit,requestSubTableData,formRef] = useJvxeMethod(requestAddOrEdit,classifyIntoFormData,tableRefs,activeKey,refKeys,validateSubForm);

    //设置标题
    const title = computed(() => (!unref(isUpdate) ? '新增' : '编辑'));

    async function reset(){
      await resetFields();
      activeKey.value = ref('cesOrderGoods');
      cesOrderGoodsTable.dataSource = [];
      cesOrderCustomerForm.value.resetFields();
    }
    function classifyIntoFormData(allValues) {
         let main = Object.assign({}, allValues.formValue)
         return {
           ...main, // 展开
           cesOrderGoodsList: allValues.tablesValue[0].tableData,
           cesOrderCustomerList: cesOrderCustomerForm.value.getFormData(),
         }
       }
     //校验所有一对一子表表单
     function validateSubForm(allValues){
         return new Promise((resolve,reject)=>{
             Promise.all([
                  cesOrderCustomerForm.value.validateForm(1),
             ]).then(() => {
                 resolve(allValues)
             }).catch(e => {
                 if (e.error === VALIDATE_FAILED) {
                     // 如果有未通过表单验证的子表，就自动跳转到它所在的tab
                     activeKey.value = e.index == null ? unref(activeKey) : refKeys.value[e.index]
                 } else {
                     console.error(e)
                 }
             })
         })
     }
    //表单提交事件
    async function requestAddOrEdit(values) {
        try {
            setModalProps({confirmLoading: true});
            //提交表单
            await saveOrUpdate(values, isUpdate.value);
            //关闭弹窗
            closeModal();
            //刷新列表
            emit('success');
        } finally {
            setModalProps({confirmLoading: false});
        }
    }
</script>

<style lang="less" scoped>

</style>