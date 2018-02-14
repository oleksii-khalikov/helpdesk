(function () {
  'use strict';

  angular.module('Tickets')
    .service('TicketService', ['$http', '$cookieStore', '$userProvider'
      , function ($http, $cookieStore, $userProvider) {

        this.getCurrentUser = function () {
          var result;
          if ($cookieStore.get('HelpDeskUser')) {
            return angular.fromJson($cookieStore.get('HelpDeskUser'));
          } else {

          }
        };

        this.getTicketsList = function () {
          var nUserId = $userProvider.getUserId();
          return $http({
            method: 'GET',
            url: '/api/tickets/list',
            params: {
              nUserId: nUserId
            }
          }).then(function (resp) {
            return resp;
          }, function (err) {
            return err;
          });
        };

        this.getTicket = function (nId) {
          return $http({
            method: 'GET',
            url: '/api/tickets/get',
            params: {
              nTicketId: nId,
              nUserId: $userProvider.getUserId()
            }
          }).then(function (resp) {
            return angular.fromJson(resp.data);
          }, function (err) {
            console.error(angular.toJson(err));
            return err;
          })
        };

        this.createTicket = function (newTicket, isDraft) {
          var json = angular.toJson({
            nCategory: newTicket.nCategory,
            sName: newTicket.sName,
            sDescription: newTicket.sDescription,
            sUrgency: newTicket.sUrgency,
            dDesiredDate: newTicket.dDesiredDate,
            sComment: newTicket.sComment,
            sState: isDraft ? 'DRAFT' : 'NEW'
          });
          return $http({
            method: 'POST',
            url: '/api/tickets/create',
            params: {
              nUserId: $userProvider.getUserId()
            },
            data: json
          }).then(function (resp) {
            return resp;
          }, function (err) {
            console.error(angular.toJson(err));
            return err;
          })
        };

        this.updateTicket = function (newTicket, isDraft) {
          var json = angular.toJson({
            nCategory: newTicket.nCategory,
            sName: newTicket.sName,
            sDescription: newTicket.sDescription,
            sUrgency: newTicket.sUrgency,
            dDesiredDate: newTicket.dDesiredDate,
            sComment: newTicket.sComment,
            sState: isDraft ? 'DRAFT' : 'NEW'
          });
          return $http({
            method: 'PUT',
            url: '/api/tickets/update',
            params: {
              nTicketId: newTicket['nId'],
              nUserId: $userProvider.getUserId()
            },
            data: json
          }).then(function (resp) {
            return resp;
          }, function (err) {
            console.error(angular.toJson(err));
            return err;
          })
        };

        this.getCategories = function () {
          var self = this;
          return $http({
            method: 'GET',
            url: '/api/enums/categories'
          }).then(function (responce) {
            return angular.fromJson(responce.data);
          }, function (err) {
            console.error(angular.toJson(err))
          });
        };

        this.getUrgencyList = function () {
          return $http({
            method: 'GET',
            url: '/api/enums/urgency'
          }).then(function (resp) {
            return angular.fromJson(resp.data);
          }, function (err) {
            console.error(angular.toJson(err))
          })
        };

        this.removeAttachmentByid = function (nId) {
          return $http({
            method: 'DELETE',
            url: '/api/file/remove',
            paramS: {
              nAttachmentId: nId
            }
          })
        };

        this.sortObjectArrayByField = function (array, field) {
          if (!((angular.isArray(array) && angular.isString(field)) && angular.isObject(array[0]))) {
            return array;
          }
          return array.sort(function (o1, o2) {
            return o1[field] - o2[field];
          })
        }




      }])
})();