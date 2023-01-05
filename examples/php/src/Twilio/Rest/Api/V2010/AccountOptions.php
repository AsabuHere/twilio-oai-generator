<?php
/**
 * This code was generated by
 * ___ _ _ _ _ _    _ ____    ____ ____ _    ____ ____ _  _ ____ ____ ____ ___ __   __
 *  |  | | | | |    | |  | __ |  | |__| | __ | __ |___ |\ | |___ |__/ |__|  | |  | |__/
 *  |  |_|_| | |___ | |__|    |__| |  | |    |__] |___ | \| |___ |  \ |  |  | |__| |  \
 *
 * Twilio - Accounts
 * This is the public Twilio REST API.
 *
 * NOTE: This class is auto generated by OpenAPI Generator.
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */

namespace Twilio\Rest\Api\V2010;

use Twilio\Exceptions\TwilioException;
use Twilio\ListResource;
use Twilio\InstanceResource;
use Twilio\Options;
use Twilio\Stream;
use Twilio\Values;
use Twilio\Version;
use Twilio\InstanceContext;
use Twilio\Deserialize;
use Twilio\Serialize;
use Twilio\Rest\Api\V2010\Account\CallList;

abstract class AccountOptions {
    /**
     * @param string $recordingStatusCallback  
     * @param string[] $recordingStatusCallbackEvent  
     * @param string $xTwilioWebhookEnabled  
     * @return CreateAccountOptions Options builder
     */
    public static function create(string $recordingStatusCallback = Values::NONE, array $recordingStatusCallbackEvent = Values::ARRAY_NONE, string $xTwilioWebhookEnabled = Values::NONE): CreateAccountOptions {
        return new CreateAccountOptions($recordingStatusCallback, $recordingStatusCallbackEvent, $xTwilioWebhookEnabled);
    }



    /**
     * @param \DateTime $dateCreated  
     * @param \DateTime $dateTest  
     * @param \DateTime $dateCreatedBefore  
     * @param \DateTime $dateCreatedAfter  
     * @return ReadAccountOptions Options builder
     */
    public static function read(\DateTime $dateCreated = Values::NONE, \DateTime $dateTest = Values::NONE, \DateTime $dateCreatedBefore = Values::NONE, \DateTime $dateCreatedAfter = Values::NONE): ReadAccountOptions {
        return new ReadAccountOptions($dateCreated, $dateTest, $dateCreatedBefore, $dateCreatedAfter);
    }

    /**
     * @param string $pauseBehavior  
     * @return UpdateAccountOptions Options builder
     */
    public static function update(string $pauseBehavior = Values::NONE): UpdateAccountOptions {
        return new UpdateAccountOptions($pauseBehavior);
    }

}

class CreateAccountOptions extends Options {
    /**
     * @param string $recordingStatusCallback 
     * @param string[] $recordingStatusCallbackEvent 
     * @param string $xTwilioWebhookEnabled 
     */
    public function __construct(string $recordingStatusCallback = Values::NONE, array $recordingStatusCallbackEvent = Values::ARRAY_NONE, string $xTwilioWebhookEnabled = Values::NONE) {
        $this->options['recordingStatusCallback'] = $recordingStatusCallback;
        $this->options['recordingStatusCallbackEvent'] = $recordingStatusCallbackEvent;
        $this->options['xTwilioWebhookEnabled'] = $xTwilioWebhookEnabled;
    }

    /**
     * @param string $recordingStatusCallback 
     * @return $this Fluent Builder
     */
    public function setRecordingStatusCallback(string $recordingStatusCallback): self {
        $this->options['recordingStatusCallback'] = $recordingStatusCallback;
        return $this;
    }

    /**
     * @param string[] $recordingStatusCallbackEvent 
     * @return $this Fluent Builder
     */
    public function setRecordingStatusCallbackEvent(array $recordingStatusCallbackEvent): self {
        $this->options['recordingStatusCallbackEvent'] = $recordingStatusCallbackEvent;
        return $this;
    }

    /**
     * @param string $xTwilioWebhookEnabled 
     * @return $this Fluent Builder
     */
    public function setXTwilioWebhookEnabled(string $xTwilioWebhookEnabled): self {
        $this->options['xTwilioWebhookEnabled'] = $xTwilioWebhookEnabled;
        return $this;
    }

    /**
     * Provide a friendly representation
     *
     * @return string Machine friendly representation
     */
    public function __toString(): string {
        $options = \http_build_query(Values::of($this->options), '', ' ');
        return '[Twilio.Api.V2010.CreateAccountOptions ' . $options . ']';
    }
}



class ReadAccountOptions extends Options {
    /**
     * @param \DateTime $dateCreated 
     * @param \DateTime $dateTest 
     * @param \DateTime $dateCreatedBefore 
     * @param \DateTime $dateCreatedAfter 
     */
    public function __construct(\DateTime $dateCreated = Values::NONE, \DateTime $dateTest = Values::NONE, \DateTime $dateCreatedBefore = Values::NONE, \DateTime $dateCreatedAfter = Values::NONE) {
        $this->options['dateCreated'] = $dateCreated;
        $this->options['dateTest'] = $dateTest;
        $this->options['dateCreatedBefore'] = $dateCreatedBefore;
        $this->options['dateCreatedAfter'] = $dateCreatedAfter;
    }

    /**
     * @param \DateTime $dateCreated 
     * @return $this Fluent Builder
     */
    public function setDateCreated(\DateTime $dateCreated): self {
        $this->options['dateCreated'] = $dateCreated;
        return $this;
    }

    /**
     * @param \DateTime $dateTest 
     * @return $this Fluent Builder
     */
    public function setDateTest(\DateTime $dateTest): self {
        $this->options['dateTest'] = $dateTest;
        return $this;
    }

    /**
     * @param \DateTime $dateCreatedBefore 
     * @return $this Fluent Builder
     */
    public function setDateCreatedBefore(\DateTime $dateCreatedBefore): self {
        $this->options['dateCreatedBefore'] = $dateCreatedBefore;
        return $this;
    }

    /**
     * @param \DateTime $dateCreatedAfter 
     * @return $this Fluent Builder
     */
    public function setDateCreatedAfter(\DateTime $dateCreatedAfter): self {
        $this->options['dateCreatedAfter'] = $dateCreatedAfter;
        return $this;
    }

    /**
     * Provide a friendly representation
     *
     * @return string Machine friendly representation
     */
    public function __toString(): string {
        $options = \http_build_query(Values::of($this->options), '', ' ');
        return '[Twilio.Api.V2010.ReadAccountOptions ' . $options . ']';
    }
}

class UpdateAccountOptions extends Options {
    /**
     * @param string $pauseBehavior 
     */
    public function __construct(string $pauseBehavior = Values::NONE) {
        $this->options['pauseBehavior'] = $pauseBehavior;
    }

    /**
     * @param string $pauseBehavior 
     * @return $this Fluent Builder
     */
    public function setPauseBehavior(string $pauseBehavior): self {
        $this->options['pauseBehavior'] = $pauseBehavior;
        return $this;
    }

    /**
     * Provide a friendly representation
     *
     * @return string Machine friendly representation
     */
    public function __toString(): string {
        $options = \http_build_query(Values::of($this->options), '', ' ');
        return '[Twilio.Api.V2010.UpdateAccountOptions ' . $options . ']';
    }
}

