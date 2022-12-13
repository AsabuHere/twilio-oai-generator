<?php
/*
 * This code was generated by
 * ___ _ _ _ _ _    _ ____    ____ ____ _    ____ ____ _  _ ____ ____ ____ ___ __   __
 *  |  | | | | |    | |  | __ |  | |__| | __ | __ |___ |\ | |___ |__/ |__|  | |  | |__/
 *  |  |_|_| | |___ | |__|    |__| |  | |    |__] |___ | \| |___ |  \ |  |  | |__| |  \
 *
 * Twilio - Versionless
 * No description provided (generated by Openapi Generator https://github.com/openapitools/openapi-generator)
 *
 * NOTE: This class is auto generated by OpenAPI Generator.
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */

namespace Twilio\Rest\Versionless;

use Twilio\Domain;
use Twilio\Exceptions\TwilioException;
use Twilio\InstanceContext;
use Twilio\Rest\Versionless\DeployedDevices\FleetList;
use Twilio\Version;

/**
 * @property FleetList $fleets
 * @method \Twilio\Rest\Versionless\DeployedDevices\FleetContext fleets(string $sid)
 */

class DeployedDevices extends Version {
    protected $_fleets;

    /**
     * Construct the DeployedDevices version of Versionless
     *
     * @param Domain $domain Domain that contains the version
     */
    public function __construct(Domain $domain) {
        parent::__construct($domain);
        $this->version = 'DeployedDevices';
    }

    protected function getFleets(): FleetList {
        if (!$this->_fleets) {
            $this->_fleets = new FleetList($this);
        }
        return $this->_fleets;
    }

    /**
     * Magic getter to lazy load root resources
     *
     * @param string $name Resource to return
     * @return \Twilio\ListResource The requested resource
     * @throws TwilioException For unknown resource
     */
    public function __get(string $name) {
        $method = 'get' . \ucfirst($name);
        if (\method_exists($this, $method)) {
            return $this->$method();
        }

        throw new TwilioException('Unknown resource ' . $name);
    }

    /**
     * Magic caller to get resource contexts
     *
     * @param string $name Resource to return
     * @param array $arguments Context parameters
     * @return InstanceContext The requested resource context
     * @throws TwilioException For unknown resource
     */
    public function __call(string $name, array $arguments): InstanceContext {
        $property = $this->$name;
        if (\method_exists($property, 'getContext')) {
            return \call_user_func_array(array($property, 'getContext'), $arguments);
        }

        throw new TwilioException('Resource does not have a context');
    }

    /**
     * Provide a friendly representation
     *
     * @return string Machine friendly representation
     */
    public function __toString(): string {
        return '[Twilio.Versionless.DeployedDevices]';
    }
}