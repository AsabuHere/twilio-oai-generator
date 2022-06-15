// Code generated by MockGen. DO NOT EDIT.
// Source: client/provider.go

// Package client is a generated GoMock package.
package client

import (
	openapi "go-client/helper/rest/api/v2010"
	reflect "reflect"

	gomock "github.com/golang/mock/gomock"
)

// MockApiV2010 is a mock of ApiV2010 interface.
type MockApiV2010 struct {
	ctrl     *gomock.Controller
	recorder *MockApiV2010MockRecorder
}

// MockApiV2010MockRecorder is the mock recorder for MockApiV2010.
type MockApiV2010MockRecorder struct {
	mock *MockApiV2010
}

// NewMockApiV2010 creates a new mock instance.
func NewMockApiV2010(ctrl *gomock.Controller) *MockApiV2010 {
	mock := &MockApiV2010{ctrl: ctrl}
	mock.recorder = &MockApiV2010MockRecorder{mock}
	return mock
}

// EXPECT returns an object that allows the caller to indicate expected use.
func (m *MockApiV2010) EXPECT() *MockApiV2010MockRecorder {
	return m.recorder
}

// CreateAccount mocks base method.
func (m *MockApiV2010) CreateAccount(params *openapi.CreateAccountParams) (*openapi.TestResponseObject, error) {
	m.ctrl.T.Helper()
	ret := m.ctrl.Call(m, "CreateAccount", params)
	ret0, _ := ret[0].(*openapi.TestResponseObject)
	ret1, _ := ret[1].(error)
	return ret0, ret1
}

// CreateAccount indicates an expected call of CreateAccount.
func (mr *MockApiV2010MockRecorder) CreateAccount(params interface{}) *gomock.Call {
	mr.mock.ctrl.T.Helper()
	return mr.mock.ctrl.RecordCallWithMethodType(mr.mock, "CreateAccount", reflect.TypeOf((*MockApiV2010)(nil).CreateAccount), params)
}

// CreateCall mocks base method.
func (m *MockApiV2010) CreateCall(params *openapi.CreateCallParams) (*openapi.TestResponseObject, error) {
	m.ctrl.T.Helper()
	ret := m.ctrl.Call(m, "CreateCall", params)
	ret0, _ := ret[0].(*openapi.TestResponseObject)
	ret1, _ := ret[1].(error)
	return ret0, ret1
}

// CreateCall indicates an expected call of CreateCall.
func (mr *MockApiV2010MockRecorder) CreateCall(params interface{}) *gomock.Call {
	mr.mock.ctrl.T.Helper()
	return mr.mock.ctrl.RecordCallWithMethodType(mr.mock, "CreateCall", reflect.TypeOf((*MockApiV2010)(nil).CreateCall), params)
}

// CreateCredentialAws mocks base method.
func (m *MockApiV2010) CreateCredentialAws(params *openapi.CreateCredentialAwsParams) (*openapi.TestResponseObject, error) {
	m.ctrl.T.Helper()
	ret := m.ctrl.Call(m, "CreateCredentialAws", params)
	ret0, _ := ret[0].(*openapi.TestResponseObject)
	ret1, _ := ret[1].(error)
	return ret0, ret1
}

// CreateCredentialAws indicates an expected call of CreateCredentialAws.
func (mr *MockApiV2010MockRecorder) CreateCredentialAws(params interface{}) *gomock.Call {
	mr.mock.ctrl.T.Helper()
	return mr.mock.ctrl.RecordCallWithMethodType(mr.mock, "CreateCredentialAws", reflect.TypeOf((*MockApiV2010)(nil).CreateCredentialAws), params)
}

// DeleteAccount mocks base method.
func (m *MockApiV2010) DeleteAccount(Sid string) error {
	m.ctrl.T.Helper()
	ret := m.ctrl.Call(m, "DeleteAccount", Sid)
	ret0, _ := ret[0].(error)
	return ret0
}

// DeleteAccount indicates an expected call of DeleteAccount.
func (mr *MockApiV2010MockRecorder) DeleteAccount(Sid interface{}) *gomock.Call {
	mr.mock.ctrl.T.Helper()
	return mr.mock.ctrl.RecordCallWithMethodType(mr.mock, "DeleteAccount", reflect.TypeOf((*MockApiV2010)(nil).DeleteAccount), Sid)
}

// DeleteCall mocks base method.
func (m *MockApiV2010) DeleteCall(Sid int, params *openapi.DeleteCallParams) error {
	m.ctrl.T.Helper()
	ret := m.ctrl.Call(m, "DeleteCall", Sid, params)
	ret0, _ := ret[0].(error)
	return ret0
}

// DeleteCall indicates an expected call of DeleteCall.
func (mr *MockApiV2010MockRecorder) DeleteCall(Sid, params interface{}) *gomock.Call {
	mr.mock.ctrl.T.Helper()
	return mr.mock.ctrl.RecordCallWithMethodType(mr.mock, "DeleteCall", reflect.TypeOf((*MockApiV2010)(nil).DeleteCall), Sid, params)
}

// DeleteCredentialAws mocks base method.
func (m *MockApiV2010) DeleteCredentialAws(Sid string) error {
	m.ctrl.T.Helper()
	ret := m.ctrl.Call(m, "DeleteCredentialAws", Sid)
	ret0, _ := ret[0].(error)
	return ret0
}

// DeleteCredentialAws indicates an expected call of DeleteCredentialAws.
func (mr *MockApiV2010MockRecorder) DeleteCredentialAws(Sid interface{}) *gomock.Call {
	mr.mock.ctrl.T.Helper()
	return mr.mock.ctrl.RecordCallWithMethodType(mr.mock, "DeleteCredentialAws", reflect.TypeOf((*MockApiV2010)(nil).DeleteCredentialAws), Sid)
}

// FetchAccount mocks base method.
func (m *MockApiV2010) FetchAccount(Sid string) (*openapi.TestResponseObject, error) {
	m.ctrl.T.Helper()
	ret := m.ctrl.Call(m, "FetchAccount", Sid)
	ret0, _ := ret[0].(*openapi.TestResponseObject)
	ret1, _ := ret[1].(error)
	return ret0, ret1
}

// FetchAccount indicates an expected call of FetchAccount.
func (mr *MockApiV2010MockRecorder) FetchAccount(Sid interface{}) *gomock.Call {
	mr.mock.ctrl.T.Helper()
	return mr.mock.ctrl.RecordCallWithMethodType(mr.mock, "FetchAccount", reflect.TypeOf((*MockApiV2010)(nil).FetchAccount), Sid)
}

// FetchCall mocks base method.
func (m *MockApiV2010) FetchCall(Sid int, params *openapi.FetchCallParams) (*openapi.TestResponseObject, error) {
	m.ctrl.T.Helper()
	ret := m.ctrl.Call(m, "FetchCall", Sid, params)
	ret0, _ := ret[0].(*openapi.TestResponseObject)
	ret1, _ := ret[1].(error)
	return ret0, ret1
}

// FetchCall indicates an expected call of FetchCall.
func (mr *MockApiV2010MockRecorder) FetchCall(Sid, params interface{}) *gomock.Call {
	mr.mock.ctrl.T.Helper()
	return mr.mock.ctrl.RecordCallWithMethodType(mr.mock, "FetchCall", reflect.TypeOf((*MockApiV2010)(nil).FetchCall), Sid, params)
}

// FetchCredentialAws mocks base method.
func (m *MockApiV2010) FetchCredentialAws(Sid string) (*openapi.TestResponseObject, error) {
	m.ctrl.T.Helper()
	ret := m.ctrl.Call(m, "FetchCredentialAws", Sid)
	ret0, _ := ret[0].(*openapi.TestResponseObject)
	ret1, _ := ret[1].(error)
	return ret0, ret1
}

// FetchCredentialAws indicates an expected call of FetchCredentialAws.
func (mr *MockApiV2010MockRecorder) FetchCredentialAws(Sid interface{}) *gomock.Call {
	mr.mock.ctrl.T.Helper()
	return mr.mock.ctrl.RecordCallWithMethodType(mr.mock, "FetchCredentialAws", reflect.TypeOf((*MockApiV2010)(nil).FetchCredentialAws), Sid)
}

// UpdateAccount mocks base method.
func (m *MockApiV2010) UpdateAccount(Sid string, params *openapi.UpdateAccountParams) (*openapi.TestResponseObject, error) {
	m.ctrl.T.Helper()
	ret := m.ctrl.Call(m, "UpdateAccount", Sid, params)
	ret0, _ := ret[0].(*openapi.TestResponseObject)
	ret1, _ := ret[1].(error)
	return ret0, ret1
}

// UpdateAccount indicates an expected call of UpdateAccount.
func (mr *MockApiV2010MockRecorder) UpdateAccount(Sid, params interface{}) *gomock.Call {
	mr.mock.ctrl.T.Helper()
	return mr.mock.ctrl.RecordCallWithMethodType(mr.mock, "UpdateAccount", reflect.TypeOf((*MockApiV2010)(nil).UpdateAccount), Sid, params)
}

// UpdateCredentialAws mocks base method.
func (m *MockApiV2010) UpdateCredentialAws(Sid string, params *openapi.UpdateCredentialAwsParams) (*openapi.TestResponseObject, error) {
	m.ctrl.T.Helper()
	ret := m.ctrl.Call(m, "UpdateCredentialAws", Sid, params)
	ret0, _ := ret[0].(*openapi.TestResponseObject)
	ret1, _ := ret[1].(error)
	return ret0, ret1
}

// UpdateCredentialAws indicates an expected call of UpdateCredentialAws.
func (mr *MockApiV2010MockRecorder) UpdateCredentialAws(Sid, params interface{}) *gomock.Call {
	mr.mock.ctrl.T.Helper()
	return mr.mock.ctrl.RecordCallWithMethodType(mr.mock, "UpdateCredentialAws", reflect.TypeOf((*MockApiV2010)(nil).UpdateCredentialAws), Sid, params)
}